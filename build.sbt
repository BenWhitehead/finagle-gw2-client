organization := "com.github.benwhitehead"

name := "finagle-gw2-client"

version := "0.1-SNAPSHOT"

scalaVersion := "2.10.3"

scalacOptions ++= Seq("-unchecked", "-deprecation")

javacOptions ++= Seq("-source", "1.6", "-target", "1.6")

javacOptions in doc := Seq("-source", "1.6")

resolvers += "Twitter" at "http://maven.twttr.com/"

libraryDependencies ++= Seq(
  "com.twitter"                   % "finagle-http_2.10"           % "6.9.0"   excludeAll(
      ExclusionRule(organization = "org.scala-tools.testing"),
      ExclusionRule(organization = "junit"),
      ExclusionRule(organization = "org.mockito")
    ),
  "com.fasterxml.jackson.module"  % "jackson-module-scala_2.10"   % "2.3.0",
  "com.github.theon"              %% "scala-uri"                  % "0.3.6",
  "org.scalatest"                 %% "scalatest"                  % "2.0"     % "test"
)

parallelExecution in Test := true

publishMavenStyle := true

pomExtra :=
  <url>https://github.com/BenWhitehead/finagle-gw2-client</url>
  <licenses>
    <license>
      <name>Apache 2</name>
      <url>http://www.apache.org/licenses/LICENSE-2.0.txt</url>
      <distribution>repo</distribution>
      <comments>A business-friendly OSS license</comments>
    </license>
  </licenses>
  <scm>
    <url>git@github.com:BenWhitehead/finagle-gw2-client.git</url>
    <connection>scm:git:git@github.com:BenWhitehead/finagle-gw2-client.git</connection>
  </scm>
  <developers>
    <developer>
      <id>BenWhitehead</id>
      <name>Ben Whitehead</name>
      <url>https://github.com/BenWhitehead</url>
    </developer>
  </developers>

