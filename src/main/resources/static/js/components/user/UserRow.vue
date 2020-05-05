<template>
    <v-container>
        <v-card
                class="d-flex flex-row mb-6"
                flat
                tile
        >
            <v-list>
                <v-list-item>Username: {{user.username}}</v-list-item>
                <v-list-item>Role: {{user.role}}</v-list-item>
            </v-list>
        </v-card>
        <v-divider/>
    </v-container>
</template>

<script>

    export default {
        name: 'UserRow',
        props: ['user'],
        data() {
            return {}
        }

    }
</script>

<style scoped>

</style>