package com.example.tsj.adapters.references

import com.example.tsj.service.model.ReferenceLiteModel

interface ReferencesListener {
    fun onClickItem(item: ReferenceLiteModel)
}