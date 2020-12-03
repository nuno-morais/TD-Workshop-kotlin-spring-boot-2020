package com.talkdesk.billstracker.services

import com.talkdesk.billstracker.entities.Bill
import com.talkdesk.billstracker.entities.BillStatEvent
import com.talkdesk.billstracker.entities.EventStatusCode
import com.talkdesk.billstracker.exceptions.NotFoundException
import com.talkdesk.billstracker.repositories.BillsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service




@Service
class BillsService(

    private val billsRepository: BillsRepository,

    private val statusPublisher: StatusPublisher

//    // statuspublisher
//    @Autowired
//    private val queue : Queue,
//    private val  template :RabbitTemplate
) {
    fun findById(id: Int): Bill = billsRepository.findById(id).let {
        if (it.isPresent) {
            it.get()
        } else throw NotFoundException()
    }

    fun findAll() = billsRepository.findAll()

    fun delete(id: Int) = findById(id).run{
        billsRepository.deleteById(id)
    }

    fun create(bill: Bill): Bill = billsRepository.save(bill)

    fun update(bill: Bill): Bill = findById(bill.id!!).let {
        billsRepository.save(bill)
        //statuspublisher.publish('UPDATED')
    }


}
@Service
class StatusPublisher(
    //rabbit channel
    private val channel: com.rabbitmq.client.Channel,
    private val objectMapper : com.fasterxml.jackson.databind.ObjectMapper
){

    fun publishCreateStatus(accountId : String) {
        send(BillStatEvent(com.talkdesk.billstracker.entities.EventStatusCode.CREATED,accountId))

    }
    fun publishUpdateStatus(accountId : String) {
        send(com.talkdesk.billstracker.entities.BillStatEvent(com.talkdesk.billstracker.entities.EventStatusCode.UPDATED, accountId))
    }
    fun publishDeleteStatus(accountId : String) {
        send(com.talkdesk.billstracker.entities.BillStatEvent(com.talkdesk.billstracker.entities.EventStatusCode.DELETED, accountId))
    }

    private fun send(status: com.talkdesk.billstracker.entities.EventStatusCode){
        val stringtosend = objectMapper.writeValueAsString(status)
        channel.basic
    }
}
