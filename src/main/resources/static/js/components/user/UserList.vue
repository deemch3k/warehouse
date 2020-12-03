<template>
  <v-container class="my-5">
    <v-layout row wrap>
      <v-flex sx6 align-self-start>
        <v-btn block top class="primary pa-2 ma-5">Users</v-btn>
        <user-row
            v-for="user in users"
            :key="user.id"
            :user="user"
        ></user-row>
      </v-flex>
      <v-flex sx6 align-self-start>
        <v-btn block top class="primary pa-2 ma-5">Admins</v-btn>
        <user-row
            v-for="user in admins"
            :key="user.id"
            :user="user"
        ></user-row>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>

import {mapGetters} from 'vuex'
import UserRow from "./UserRow.vue";

export default {
  name: 'UserList',
  components: {UserRow},
  computed: {
    ...mapGetters(['admins', 'users'])
  }
}
</script>

<style>

</style>