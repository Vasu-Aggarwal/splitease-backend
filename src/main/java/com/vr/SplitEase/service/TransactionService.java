package com.vr.SplitEase.service;

import com.vr.SplitEase.dto.request.AddTransactionRequest;
import com.vr.SplitEase.dto.request.SettleUpTransactionRequest;
import com.vr.SplitEase.dto.response.AddTransactionResponse;
import com.vr.SplitEase.dto.response.CalculatedDebtResponse;
import com.vr.SplitEase.dto.response.DeleteResponse;
import com.vr.SplitEase.dto.response.SettleUpTransactionResponse;
import com.vr.SplitEase.entity.Group;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

public interface TransactionService {
    AddTransactionResponse addTransaction(AddTransactionRequest addTransactionRequest);
    CalculatedDebtResponse calculateDebt(Integer groupId);
    SettleUpTransactionResponse settleUpTransaction(SettleUpTransactionRequest settleUpTransactionRequest);
    DeleteResponse deleteTransaction(Integer transactionId);
    AddTransactionResponse getTransactionById(Integer transactionId);
    List<AddTransactionResponse> getTransactionsByGroupId(Integer groupId);
}
