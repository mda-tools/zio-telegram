package org.mdatools.core

import org.mdatools.core.model.User
import org.mdatools.core.model.response.Response

trait TelegramBotApiClient[F[_]] {
  def getMe: F[Response[User]]
}
