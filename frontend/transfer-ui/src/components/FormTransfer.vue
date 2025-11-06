<template>
  <form @submit.prevent="submitForm" class="bg-white p-6 rounded shadow-md mb-6 w-full max-w-md">
    <div class="mb-4">
      <label class="block text-gray-700">Conta Origem</label>
      <input v-model="transfer.originAccount" class="border rounded w-full p-2" required />
    </div>
    <div class="mb-4">
      <label class="block text-gray-700">Conta Destino</label>
      <input v-model="transfer.destinationAccount" class="border rounded w-full p-2" required />
    </div>
    <div class="mb-4">
      <label class="block text-gray-700">Valor</label>
      <input type="number" step="0.01" v-model="transfer.amount" class="border rounded w-full p-2" required />
    </div>
    <div class="mb-4">
      <label class="block text-gray-700">Data Transferência</label>
      <input type="date" v-model="transfer.transferDate" class="border rounded w-full p-2" required />
    </div>
    <button class="bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700">Agendar</button>
  </form>
</template>

<script setup>
import axios from 'axios'
import { reactive } from 'vue'

const emit = defineEmits(['added'])

const transfer = reactive({
  originAccount: '',
  destinationAccount: '',
  amount: 0,
  transferDate: ''
})

const submitForm = async () => {
  try {
    await axios.post('/api/transfers', transfer)
    emit('added')
    Object.assign(transfer, { originAccount: '', destinationAccount: '', amount: 0, transferDate: '' })
  } catch (e) {
    alert('Erro ao agendar transferência')
  }
}
</script>
