<template>
  <v-container>
    <v-file-input
        accept="text/xml"
        v-model="chosenFile"
    ></v-file-input>
    <v-btn
        @click="importFile()"
    >Import products from file
    </v-btn>
    <v-alert
        :value="fileError"
        class="ma-4"
        dense
        outlined
        type="error"
    >No file chosen
    </v-alert>

    <form>
      <v-text-field
          v-model="name"
          :counter="50"
          label="Name"
          required
      >
      </v-text-field>
      <v-text-field
          v-model="totalAmount"
          label="Total Amount"
          required
      ></v-text-field>
      <v-text-field
          v-model="placement"
          label="Placement"
          required
      ></v-text-field>
      <v-menu
          ref="menu"
          v-model="menu"
          :close-on-content-click="false"
          :return-value.sync="expirationDate"
          transition="scale-transition"
          offset-y
          min-width="290px"
      >
        <template v-slot:activator="{ on }">
          <v-text-field
              v-model="expirationDate"
              label="Shelf Life"
              prepend-icon="event"
              readonly
              v-on="on"
          ></v-text-field>
        </template>
        <v-date-picker v-model="expirationDate" :min="today" no-title scrollable>
          <v-spacer></v-spacer>
          <v-btn text color="primary" @click="menu = false">Cancel</v-btn>
          <v-btn text color="primary" @click="$refs.menu.save(expirationDate)">OK</v-btn>
        </v-date-picker>
      </v-menu>
      <v-textarea
          v-model="description"
          :counter="255"
          label="Descrption"
          required
      ></v-textarea>

      <v-btn class="mr-4" @click="save">Save</v-btn>
      <v-btn @click="clear">clear</v-btn>
    </form>
    <v-alert
        :value="isCreated"
        class="ma-4"
        dense
        text
        type="success"
    >
      Product was created <strong>successfully</strong>
    </v-alert>
    <v-alert
        :value="isError"
        class="ma-4"
        dense
        outlined
        type="error"
    >
      Fields failed <strong>validation</strong>
    </v-alert>
  </v-container>
</template>

<script>

import {mapActions} from 'vuex'

export default {
  name: "ProductForm",
  props: ['productAttr'],
  computed: {
    today() {
      const today = new Date();
      return this.formatDate(today);
    }
  },
  data() {
    return {
      name: '',
      totalAmount: '',
      id: '',
      description: '',
      placement: '',
      expirationDate: new Date().toISOString().substr(0, 10),
      isCreated: false,
      isError: false,
      menu: '',
      chosenFile: null,
      fileError: false
    }
  },
  watch: {
    productAttr(newVal, oldVal) {
      this.name = newVal.name;
      this.totalAmount = newVal.totalAmount;
      this.id = newVal.id;
      this.description = newVal.description;
      this.placement = newVal.placement;
      this.expirationDate = newVal.expirationDate;
    }
  },
  methods: {
    ...mapActions(['addProductAction', 'updateProductAction']),
    save() {
      const product = {
        id: this.id,
        name: this.name,
        description: this.description,
        totalAmount: this.totalAmount,
        placement: this.placement,
        expirationDate: this.expirationDate
      };
      //todo validation ( if at least one field is empty in product -> switch isError = true )
      this.isError = true
      if (this.id) {
        this.updateProductAction(product)
      } else {
        this.addProductAction(product)
      }
      this.clear()
      this.id = '';
      this.isCreated = true
      console.log(this.$store.state.profile)
    },
    clear() {
      this.name = ''
      this.description = ''
      this.totalAmount = ''
      this.placement = ''
      this.expirationDate = ''
    },
    formatDate(date) {
      let month = `${date.getMonth() + 1}`;
      let day = `${date.getDate()}`;
      const year = date.getFullYear();

      if (month.length < 2) month = `0${month}`;
      if (day.length < 2) day = `0${day}`;

      return [year, month, day].join('-');
    },
    importFile() {
      if (!this.chosenFile) {
        this.fileError = true
      } else {
        let formData = new FormData();
        formData.append("xmlFile", this.chosenFile);
        console.log(formData);
        console.log(this.chosenFile);
        fetch('/product/import', {method: "POST", body: formData})
      }

    }
  },
}
</script>

<style scoped>

</style>