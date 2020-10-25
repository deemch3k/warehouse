<template>
  <v-container>
    <div class="search-wrapper">
      <v-text-field label="Search by order id" v-model="search"></v-text-field>
    </div>
    <div v-for="report in filteredList"
         :key="report.id"
         class="ma-5"
    >
      Order ID: {{ report.orderId }}
      <v-col></v-col>
      Reason for Cancellation: {{ report.reasonForCancellation }}
      <v-divider></v-divider>
    </div>
  </v-container>
</template>

<script>

import {mapGetters} from 'vuex'

export default {
  name: 'CancellationReportList',

  data() {
    return {
      search: ''
    }
  },
  computed: {
    ...mapGetters(['sortedReports']),
    filteredList() {
      return this.sortedReports.filter(report => {
        return report.orderId.includes(this.search)
      })
    }
  }
}

</script>

<style scoped>

</style>