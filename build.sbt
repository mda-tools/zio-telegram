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

Global / onChangedBuildSource := ReloadOnSourceChanges

lazy val core = (project in file("./core")).settings(
  name := "zio-tg-bot-api-core",
  libraryDependencies ++= Dependencies.core,
)

lazy val library = (project in file(".")).dependsOn(core)

lazy val example = (project in file("./example"))
  .settings(name := "zio-tg-bot-api-example")
  .dependsOn(library)
