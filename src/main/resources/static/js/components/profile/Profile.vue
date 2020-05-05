<template>
    <v-container fill-height fluid grid-list-xl>
        <v-layout justify-center wrap>
            <v-flex xs12 md8 align-self-start>
                <v-form>
                    <v-container>
                        <v-layout wrap>
                            <v-flex xs12 md6>
                                <v-label>Username: {{profile.username}}</v-label>
                            </v-flex>
                            <v-flex xs12 md6>
                                <v-label>Role: {{profile.role}}</v-label>
                            </v-flex>
                        </v-layout>
                    </v-container>
                </v-form>
            </v-flex>
        </v-layout>
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
</script>

<style scoped>

</style>