package uk.gov.hmrc.payesettlementagreements.models

import play.api.libs.json.Json

case class ReferenceNumber(number: String)

object ReferenceNumber {
  implicit val formats = Json.format[ReferenceNumber]
}
