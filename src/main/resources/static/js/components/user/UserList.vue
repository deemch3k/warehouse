<template>
    <v-container v-if="profile.role==='ADMIN'" class="my-5">
        <v-layout row wrap>
            <v-flex sx6 align-self-start>
                <v-btn block top class="primary pa-2 ma-5">Users</v-btn>
                <user-row
                        v-for="user in getUsers()"
                        :key="user.id"
                        :user="user"
                ></user-row>
            </v-flex>
            <v-flex sx6 align-self-start>
                <v-btn block top class="primary pa-2 ma-5">Admins</v-btn>
                <user-row
                        v-for="user in getAdmins()"
                        :key="user.id"
                        :user="user"
                ></user-row>
            </v-flex>
        </v-layout>
    </v-container>
</template>

<script>

    import {mapState} from 'vuex'
    import UserRow from "./UserRow.vue";

    export default {
        name: 'UserList',
        components: {UserRow},
        computed: {
            ...mapState(['profile', 'users']),
        },
        methods: {
            getUsers(){
                return this.$store.state.users.filter(user => user.role === 'USER')
            },
            getAdmins(){
                return this.$store.state.users.filter(user => user.role === 'ADMIN')
            }
        }
    }
</script>

<style>

</style>