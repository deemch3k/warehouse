<template>
    <form>
        <v-text-field
                v-model="name"
                :counter="50"
                label="Name"
                required
        ></v-text-field>
        <v-text-field
                v-model="surname"
                label="Surname"
                required
        ></v-text-field>
        <v-text-field
                v-model="address"
                label="Address"
                required
        ></v-text-field>
        <v-text-field
                v-model="postcode"
                label="Postcode"
                required
        ></v-text-field>
        <v-text-field
                v-model="number"
                label="Phone number"
                required
        ></v-text-field>
        <v-btn @click="setClientInfo">Set Client Info</v-btn>
    </form>
</template>

<script>
    export default {
        name: "ClientInfoForm",
        data(){
            return {
                name: '',
                surname: '',
                address: '',
                postcode: '',
                number: ''
            }
        },
        props: (['saveClientInfo']),
        methods: {
            setClientInfo(){
               const clientInfo = {
                    name: this.name,
                    surname: this.surname,
                    address: this.address,
                    postcode: this.postcode,
                    number: this.number,
                }
                this.$emit('clientInfo', clientInfo)
            }
        }

    }
</script>

<style scoped>

</style>