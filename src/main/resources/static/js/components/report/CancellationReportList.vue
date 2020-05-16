<template>
    <v-container>
        <div v-for="report in sortedReports"
             :key="report.id"
             class="ma-5"
        >
            Order ID: {{report.orderId}}
            <div v-if="isOrderByIdExist(report.orderId)">
                This Order Exist
            </div>
            <div v-else>
                This Order doesn't exist
            </div>
            <v-col></v-col>
            Reason for Cancellation: {{report.reasonForCancellation}}
            <v-divider></v-divider>
        </div>
    </v-container>
</template>

<script>

    import {mapGetters, mapState} from 'vuex'

    export default {
        name: 'CancellationReportList',
        computed: {
            ...mapGetters(['sortedReports']),
            ...mapState(['orders'])
        },
        methods: {
            isOrderByIdExist(orderId) {
                const res = this.$store.state.orders.findIndex(item => orderId === item.id.toString())
                return (res > -1)
            }
        }
    }

</script>

<style scoped>

</style>