package com.talkdesk.billstracker.repositories

import com.talkdesk.billstracker.entities.Bill
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface BillsRepository : CrudRepository<Bill, Int>
