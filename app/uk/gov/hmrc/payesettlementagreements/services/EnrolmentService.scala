package uk.gov.hmrc.payesettlementagreements.services

import javax.inject.Inject

import uk.gov.hmrc.http.HeaderCarrier
import uk.gov.hmrc.payesettlementagreements.models.{EnrolmentRequest, ReferenceNumber}

import scala.concurrent.Future

class EnrolmentService @Inject()() {
  def enrol(request: EnrolmentRequest)(implicit hc: HeaderCarrier) : Future[Either[String,ReferenceNumber]] = {
    Future.successful(Right(ReferenceNumber("XA234282349")))
  }
}
