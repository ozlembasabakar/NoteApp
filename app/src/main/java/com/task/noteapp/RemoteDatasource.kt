package com.task.noteapp

import com.task.noteapp.data.model.Note
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDatasource @Inject constructor() {
    val datasource = MutableStateFlow(
        listOf(
            Note(
                id = 1,
                title = "Todo list1",
                description = "Laundry, pay the bills, take out the trash, laundry, pay the bills, take out the trash, laundry, pay the bills, take out the trash",
                imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQX_myk5jX77-Ljy3cvcOWEVAcS_QuVL3DTXKYUmpz8hYCnzWj7j6tbd8almtUsQSxSXe0&usqp=CAU"
            ),
            Note(
                id = 2,
                title = "Todo list2",
                description = "Laundry, pay the bills, take out the trash, laundry, pay the bills, take out the trash, laundry, pay the bills, take out the trash",
                imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQX_myk5jX77-Ljy3cvcOWEVAcS_QuVL3DTXKYUmpz8hYCnzWj7j6tbd8almtUsQSxSXe0&usqp=CAU"
            ),
            Note(
                id = 3,
                title = "Todo list3",
                description = "Laundry, pay the bills, take out the trash, laundry, pay the bills, take out the trash, laundry, pay the bills, take out the trash",
                imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQX_myk5jX77-Ljy3cvcOWEVAcS_QuVL3DTXKYUmpz8hYCnzWj7j6tbd8almtUsQSxSXe0&usqp=CAU"
            ),
            Note(
                id = 4,
                title = "Todo list4",
                description = "Laundry, pay the bills, take out the trash, laundry, pay the bills, take out the trash, laundry, pay the bills, take out the trash",
                imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQX_myk5jX77-Ljy3cvcOWEVAcS_QuVL3DTXKYUmpz8hYCnzWj7j6tbd8almtUsQSxSXe0&usqp=CAU"
            ),
            Note(
                id = 5,
                title = "Todo list5",
                description = "Laundry, pay the bills, take out the trash, laundry, pay the bills, take out the trash, laundry, pay the bills, take out the trash",
                imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQX_myk5jX77-Ljy3cvcOWEVAcS_QuVL3DTXKYUmpz8hYCnzWj7j6tbd8almtUsQSxSXe0&usqp=CAU"
            )
        )
    )
}