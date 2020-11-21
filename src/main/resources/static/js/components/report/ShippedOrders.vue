<template>
  <v-container>
    <div class="search-wrapper">

      <v-menu
          ref="menu"
          v-model="menu"
          :close-on-content-click="false"
          :return-value.sync="search"
          transition="scale-transition"
          offset-y
          min-width="290px"
      >
        <template v-slot:activator="{ on }">
          <v-text-field
              v-model="search"
              label="Date"
              prepend-icon="event"
              readonly
              v-on="on"
          ></v-text-field>
        </template>
        <v-date-picker v-model="search" no-title scrollable>
          <v-spacer></v-spacer>
          <v-btn text color="primary" @click="search = ''">Clear</v-btn>
          <v-btn text color="primary" @click="menu = false">Cancel</v-btn>
          <v-btn text color="primary" @click="$refs.menu.save(search)">OK</v-btn>
        </v-date-picker>
      </v-menu>
    </div>
    <v-card v-for="order in filteredList"
            :key="order.id"
            class="ma-0 pa-0"
    >
      <v-card ripple class="pa-0 ma-0">
        <v-row>
          <v-col cols="4">
            <v-card-title>
              Order ID: {{ order.id }}
            </v-card-title>
            <v-card-subtitle>
              Shipped Date : {{ order.shippingDate }}
            </v-card-subtitle>
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
  name: "ShippedOrders",

  data() {
    return {
      search: '',
    }
  },
  computed: {
    ...mapGetters(['shippedOrders']),
    filteredList() {
      return this.shippedOrders.filter(order => {
        return order.shippingDate.includes(this.search)
      })
    }
  }

}
</script>

<style scoped>

</style>