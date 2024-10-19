ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.15"

ThisBuild / name         := "zio-tg-bot-api"
ThisBuild / organization := "org.mdatools"
ThisBuild / licenses :=
  List(("MIT", url("https://opensource.org/licenses/MIT")))

ThisBuild / developers :=
  List(
    Developer(
      "teetotu",
      "Shair Dilavar",
      "sdilavar@gmail.com",
      url("https://github.com/teetotu"),
    ),
  )

// ThisBuild / publishMavenStyle := true
// ThisBuild / publishTo         := sonatypePublishToBundle.value

Global / onChangedBuildSource := ReloadOnSourceChanges

lazy val core = (project in file("./core")).settings(
  name             := "zio-tg-bot-api-core",
  idePackagePrefix := Some("org.mdatools.core"),
)

lazy val library = (project in file("."))
  .settings(name := "zio-tg-bot-api", idePackagePrefix := Some("org.mdatools"))
