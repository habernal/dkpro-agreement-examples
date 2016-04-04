/*
 * Copyright 2016 Ivan Habernal
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.habernal;

import org.dkpro.statistics.agreement.unitizing.KrippendorffAlphaUnitizingAgreement;
import org.dkpro.statistics.agreement.unitizing.UnitizingAnnotationStudy;

/**
 * @author Ivan Habernal
 */
public class UnitizedAgreementExample
{

    public static void main(String[] args)
    {
        UnitizingAnnotationStudy study = new UnitizingAnnotationStudy(3, 10);

        int rater1 = 0;
        int rater2 = 1;
        int rater3 = 2;

        /*
         * Position 0  1  2  3  4  5  6  7  8  9
         * Rater1   |-- Premise ---|  |-Claim--|
         * Rater2   |-- Premise ---|
         * Rater3      |---Pr.--|     |-Cl.-|
         */

        study.addUnit(0, 5, rater1, "Premise");
        study.addUnit(0, 5, rater2, "Premise");
        study.addUnit(1, 4, rater3, "Premise");

        study.addUnit(6, 4, rater1, "Claim");
        study.addUnit(6, 3, rater3, "Claim");

        KrippendorffAlphaUnitizingAgreement agreement = new KrippendorffAlphaUnitizingAgreement(
                study);

        // overall agreement
        double alphaUnitized = agreement.calculateAgreement();
        System.out.println(alphaUnitized);

        // agreement for premises
        double premiseAgreement = agreement.calculateCategoryAgreement("Premise");
        System.out.println(premiseAgreement);

        // agreement for claims
        double claimAgreement = agreement.calculateCategoryAgreement("Claim");
        System.out.println(claimAgreement);
    }
}
