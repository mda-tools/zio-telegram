package org.mdatools.core.model.util

case class TelegramBotApiToken(token: String) extends AnyVal {
  override def toString: String = token
}
