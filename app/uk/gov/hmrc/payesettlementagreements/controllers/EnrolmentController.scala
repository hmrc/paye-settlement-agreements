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

package uk.gov.hmrc.payesettlementagreements.controllers

import javax.inject.Inject

import play.api.mvc.Action
import uk.gov.hmrc.payesettlementagreements.models.EnrolmentRequest
import uk.gov.hmrc.payesettlementagreements.services.EnrolmentService
import uk.gov.hmrc.play.microservice.controller.BaseController
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


class EnrolmentController @Inject()(enrolmentService: EnrolmentService) extends BaseController {
  def enrol() = Action.async(parse.json) { implicit request =>
    request.body.validate[EnrolmentRequest].fold(_ => Future.successful(BadRequest), enrolmentRequest => {
      enrolmentService.enrol(enrolmentRequest).flatMap {
        case Right(_) => {
          Future.successful(Accepted)
        }
        case Left(_) => {
          Future.successful(BadRequest)
        }
      }
    })
  }
}


