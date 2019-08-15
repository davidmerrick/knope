package com.merricklabs.knope.util

import com.merricklabs.knope.config.PersonConfig
import com.merricklabs.knope.config.SplitConfig
import com.merricklabs.knope.models.GroupTotal
import com.merricklabs.knope.models.PersonTotal
import com.merricklabs.knope.models.verizon.VerizonBill

/**
 * To create your own splits for bills, simply extend this class
 * and bind the new class into the KnopeModule
 */
open class BillSplitter(config: SplitConfig, private val bill: VerizonBill) {

    private val groupConfigs = config.groups

    private val numPeople by lazy { groupConfigs.flatMap { it.items }.size }
    private val numGroups by lazy { groupConfigs.size }

    /**
     * I split taxes evenly between myself and the rest of my family.
     * So, divide by the number of groups.
     */
    private fun splitTaxes(): Float {
        return bill.getTaxes() / numGroups
    }

    /**
     * I split surcharges evenly between myself and the rest of my family.
     * So, divide by the number of groups.
     */
    private fun splitSurcharges(): Float {
        return bill.getSurcharges() / numGroups
    }

    /**
     * I split one-time charges evenly between myself and the rest of my family.
     * So, divide by the number of groups.
     */
    private fun splitOneTimeCharges(): Float {
        return bill.getOneTimeFees() / numGroups
    }

    /**
     * I split data per person.
     * So, divide by the total number of people.
     */
    private fun splitAccountCharges(): Float {
        return bill.getAccountCharges() / numPeople
    }

    fun getGroupTotal(personBills: List<PersonConfig>): GroupTotal {
        val personTotals = personBills.map { getPersonTotal(it) }
        return GroupTotal(
                personTotals,
                splitTaxes(),
                splitSurcharges(),
                splitOneTimeCharges(),
                splitAccountCharges()
        )
    }

    private fun getPersonTotal(personBill: PersonConfig): PersonTotal {
        return PersonTotal(personBill, bill.getChargesForMtn(personBill.mtn))
    }
}