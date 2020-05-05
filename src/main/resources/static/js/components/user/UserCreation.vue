<template>
    <v-container v-if="profile.role === 'ADMIN'">
        <form>
            <v-text-field
                    v-model="username"
                    :counter="50"
                    label="Name"
                    required
            ></v-text-field>
            <v-text-field
                    v-model="password"
                    label="Password"
                    required
            ></v-text-field>
            <v-select
                    :items="roles"
                    v-model="role"
                    label="Standard"
                    dense
            ></v-select>
            <v-alert
                    :value="isCreated"
                    class="ma-4"
                    dense
                    text
                    type="success"
            >
                User was created <strong>successfully</strong>.
            </v-alert>
            <v-alert
                    :value="isError"
                    class="ma-4"
                    dense
                    outlined
                    type="error"
            >
                User with entered parameters  <strong>already exist</strong>
            </v-alert>
            <v-btn class="mr-4" @click="save">Save</v-btn>
            <v-btn class="mr-4" @click="clear">Clear</v-btn>
        </form>
    </v-container>
    <div v-else>
        You need to login as Admin to visit this page
    </div>
</template>

<script>

    import {mapState, mapActions} from 'vuex'

    export default {
        name: 'UserCreation',
        computed: {
            ...mapState(['profile']),
        },
        data() {
            return {
                username: '',
                password: '',
                role: '',
                roles: ['USER', 'ADMIN'],
                isCreated: false,
                isError: false
            }
        },
        methods: {
            ...mapActions(['addUserAction']),
            async save() {
                const user = {
                    username: this.username,
                    password: this.password,
                    role: this.role,
                }
                var res = await this.addUserAction(user)

                if(res === 'OK') {
                    this.isCreated = true
                    this.clear()
                } else {
                    //todo check if user already exist
                   this.isError = true
                }
            },
            clear(){
                this.username = ''
                this.password = ''
                this.role = ''
            }
        }
    }

    //todo as Admin I can create user with role 'USER'
</script>

<style scoped>

</style>