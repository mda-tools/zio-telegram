import sbt.*

object Dependencies {

  private object versions {
    val slf4j     = "2.0.16"
    val scalatest = "3.2.19"
    val zio       = "2.1.11"
    val sttp      = "3.10.1"
  }

  private val zio = "dev.zio" %% "zio" % versions.zio

  private val http = Seq(
    "com.softwaremill.sttp.client3" %% "zio"                  % versions.sttp,
    "com.softwaremill.sttp.client3" %% "circe"                % versions.sttp,
    "io.circe"                      %% "circe-generic"        % "0.14.10",
    "io.circe"                      %% "circe-generic-extras" % "0.14.4",
  )

  val core: Seq[ModuleID] = zio +: http
}
