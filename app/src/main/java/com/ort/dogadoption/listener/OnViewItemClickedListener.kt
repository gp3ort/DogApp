package com.ort.dogadoption.listener

import com.ort.dogadoption.models.Pets

interface OnViewItemClickedListener {
    fun onViewItemDetail(pet: Pets)
}