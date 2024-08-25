FROM openjdk:11-jdk

# Install dependencies
RUN apt-get update && apt-get install -y wget unzip

# Install Android SDK
RUN mkdir -p /opt/android-sdk && \
    cd /opt && \
    wget https://dl.google.com/android/repository/commandlinetools-linux-9477386_latest.zip -O android-tools.zip && \
    unzip android-tools.zip -d android-sdk && \
    rm android-tools.zip

# Set environment variables
ENV ANDROID_HOME /opt/android-sdk
ENV PATH ${ANDROID_HOME}/cmdline-tools/latest/bin:$ANDROID_HOME/platform-tools:$PATH

# Accept Android SDK licenses
RUN mkdir -p "$ANDROID_HOME/licenses" && echo "8933bad161af4178b1185d1a37fbf41ea5269c55" > "$ANDROID_HOME/licenses/android-sdk-license" && echo "d56f5187479451eabf01fb78af6dfcb131a6481e" > "$ANDROID_HOME/licenses/android-sdk-preview-license"

# Install Android SDK Build tools and platform tools
RUN sdkmanager "platform-tools" "platforms;android-33" "build-tools;33.0.0"

# Install Gradle
RUN wget https://services.gradle.org/distributions/gradle-7.4.2-bin.zip -P /tmp && \
    unzip /tmp/gradle-7.4.2-bin.zip -d /opt && \
    ln -s /opt/gradle-7.4.2/bin/gradle /usr/bin/gradle

WORKDIR /workspace

# Cache Gradle
COPY . .

CMD ["./gradlew", "assembleRelease"]
