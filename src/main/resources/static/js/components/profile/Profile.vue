<template>
    <v-container>
        <div>{{profile.username}}</div>
    </v-container>
</template>

<script>
    import {mapState} from 'vuex'
    export default {
        name: 'Profile',
        computed: {
            ...mapState(['profile']),
        }
    }
    //todo *history of processed orders
</script>

<style scoped>

</style>