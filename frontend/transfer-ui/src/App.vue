<template>
  <div class="min-h-screen flex flex-col items-center p-6">
    <h1 class="text-3xl font-bold mb-4">Agendamento de Transferências</h1>
    <FormTransfer @added="fetchTransfers" />
    <TransferList :transfers="transfers" />
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'
import FormTransfer from './components/FormTransfer.vue'
import TransferList from './components/TransferList.vue'

const transfers = ref([])

const fetchTransfers = async () => {
  const res = await axios.get('/api/transfers')
  transfers.value = res.data
}

onMounted(fetchTransfers)
</script>
