/*
 * Copyright 2017 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.payesettlementagreements.connectors

import javax.inject.Inject

import uk.gov.hmrc.http.{HeaderCarrier, HttpPost, HttpResponse}
import uk.gov.hmrc.payesettlementagreements.config.ConnectorConfig
import uk.gov.hmrc.payesettlementagreements.models.{EnrolmentRequest, ReferenceNumber}
import uk.gov.hmrc.play.config.ServicesConfig

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class EnrolmentConnector @Inject()(httpPost: HttpPost, config: ConnectorConfig) extends ServicesConfig {

  def enrol(enrolmentRequest: EnrolmentRequest)(implicit hc: HeaderCarrier) : Future[Either[String,ReferenceNumber]] = {

    lazy val uri = s"${config.baseUrl}/enrol"

    val response: Future[HttpResponse] = httpPost.POST[EnrolmentRequest,HttpResponse](uri,enrolmentRequest)

    response.map(f=>
      f.status match {
        case 200 => {
          Right(f.json.as[ReferenceNumber])
        }
      }
    )
  }
}
