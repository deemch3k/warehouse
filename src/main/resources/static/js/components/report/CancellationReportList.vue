<template>
  <v-container>
    <div class="search-wrapper">
      <v-text-field label="Search by order id" v-model="search"></v-text-field>
    </div>

    <v-card v-for="order in this.filteredList"
            :key="order.id"
            class="ma-0 pa-0"
    >
      <v-card ripple class="pa-0 ma-0">
        <v-row>
          <v-col cols="4">
            <v-card-title>
              Order ID: {{ order.id }}
            </v-card-title>
            <v-card-text>
              Reason of Cancellation : {{ findReasonById(order.id) }}
            </v-card-text>
            <v-card-text>
              Date of Cancellation : {{ findDateOfCancellationById(order.id) }}
            </v-card-text>
            <v-card-text>
              User : {{ order.user.username }}
            </v-card-text>
          </v-col>
          <v-col cols="4">
            <v-card-title>
              Ordered products
            </v-card-title>
            <v-col v-for="pq in order.productQuantities"
                   :key="pq.id">
              <v-list>
                <v-list-item-title>Product</v-list-item-title>
                <v-list-item-subtitle>Name: {{ pq.orderedProduct.name }}</v-list-item-subtitle>
                <v-list-item-subtitle>Quantity: {{ pq.qty }}</v-list-item-subtitle>
                <v-list-item-subtitle>Placement: {{ pq.orderedProduct.placement }}
                </v-list-item-subtitle>
                <v-list-item-subtitle>Expiration Date: {{ pq.orderedProduct.expirationDate }}
                </v-list-item-subtitle>
              </v-list>
            </v-col>
          </v-col>
          <v-col cols="4" class="justify-center">
            <v-card-title>Client info</v-card-title>
            <v-card-subtitle>
              <v-list>
                <v-list-item-title>Client Info</v-list-item-title>
                <v-list-item-subtitle>Name: {{ order.clientInfo.name }}</v-list-item-subtitle>
                <v-list-item-subtitle>Surname: {{ order.clientInfo.surname }}</v-list-item-subtitle>
                <v-list-item-subtitle>Address: {{ order.clientInfo.address }}</v-list-item-subtitle>
                <v-list-item-subtitle>Postcode: {{ order.clientInfo.postcode }}</v-list-item-subtitle>
                <v-list-item-subtitle>Number: {{ order.clientInfo.number }}</v-list-item-subtitle>
              </v-list>
            </v-card-subtitle>
          </v-col>
        </v-row>
      </v-card>
      <v-divider></v-divider>
    </v-card>
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
    ...mapGetters(['sortedReports', 'canceledOrders']),
    filteredList() {
      return this.canceledOrders.filter(order => {
        return order.id.toString().includes(this.search)
      })
    },
  },
  methods: {
    findReasonById(id) {
      return this.sortedReports.filter(r => r.orderId === id.toString())[0].reasonForCancellation;
    },
    findDateOfCancellationById(id){
      return this.sortedReports.filter(r => r.orderId === id.toString())[0].dateOfCancellation;
    }
  }
}

</script>

<style scoped>

</style>