package org.mdatools.core.model.response

case class Response[R](
    ok: Boolean,
    description: Option[String] = None,
    result: Option[R],
    errorCode: Option[Int],
)
