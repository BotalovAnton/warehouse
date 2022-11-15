FROM bellsoft/liberica-openjdk-alpine-musl:11.0.6-10

ARG ARTIFACT
ENV JAR_FILE $ARTIFACT
ADD $ARTIFACT /app/
CMD exec java $JVM_OPTS -jar /app/$JAR_FILE