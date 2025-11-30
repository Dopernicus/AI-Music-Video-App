#!/usr/bin/env sh

# Gradle startup script for UN*X systems

DEFAULT_JVM_OPTS=""

APP_BASE_NAME=`basename "$0"`
APP_HOME=`dirname "$0"`

# Collect all arguments for the java command
ALL_ARGS="$@"

# If on Windows, use the .bat file instead
if [ -f "$APP_HOME/gradlew.bat" ]; then
  exec sh "$APP_HOME/gradlew.bat" "$@"
fi

# Run the gradle wrapper jar
exec java $DEFAULT_JVM_OPTS -jar "$APP_HOME/gradle/wrapper/gradle-wrapper.jar" "$@"
