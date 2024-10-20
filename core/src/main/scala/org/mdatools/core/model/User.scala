package org.mdatools.core.model

import io.circe.{Decoder, Encoder}
import io.circe.generic.extras.{AutoDerivation, Configuration}

final case class User(
    id: Long,
    isBot: Boolean,
    firstName: String,
    lastName: Option[String] = Option.empty,
    username: Option[String] = Option.empty,
    languageCode: Option[String] = Option.empty,
    isPremium: Option[Boolean] = Option.empty,
    addedToAttachmentMenu: Option[Boolean] = Option.empty,
    canJoinGroups: Option[Boolean] = Option.empty,
    canReadAllGroupMessages: Option[Boolean] = Option.empty,
    supportsInlineQueries: Option[Boolean] = Option.empty,
    canConnectToBusiness: Option[Boolean] = Option.empty,
    hasMainWebApp: Option[Boolean] = Option.empty,
)

object User extends AutoDerivation {
  implicit val config: Configuration = Configuration.default.withSnakeCaseMemberNames
  implicit val decoder: Decoder[User] = exportDecoder[User].instance
  implicit val encoder: Encoder[User] = exportEncoder[User].instance
}