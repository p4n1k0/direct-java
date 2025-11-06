<template>
  <div class="bg-white p-6 rounded shadow-md w-full max-w-2xl">
    <h2 class="text-xl font-semibold mb-3">Transferências Agendadas</h2>
    <table class="w-full border">
      <thead>
        <tr class="bg-gray-200">
          <th class="p-2 border">Origem</th>
          <th class="p-2 border">Destino</th>
          <th class="p-2 border">Valor</th>
          <th class="p-2 border">Taxa</th>
          <th class="p-2 border">Data</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="t in transfers" :key="t.id">
          <td class="p-2 border">{{ t.originAccount }}</td>
          <td class="p-2 border">{{ t.destinationAccount }}</td>
          <td class="p-2 border">{{ t.amount.toFixed(2) }}</td>
          <td class="p-2 border">{{ t.fee.toFixed(2) }}%</td>
          <td class="p-2 border">{{ t.transferDate }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup>
defineProps({ transfers: Array })
</script>
