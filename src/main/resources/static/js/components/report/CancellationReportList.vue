<template>
    <v-container>
        <div v-for="report in sortedReports"
             :key="report.id"
             class="ma-5"
        >
           Order ID: {{report.orderId}}
            <v-col></v-col>
           Reason for Cancellation: {{report.reasonForCancellation}}
            <v-divider></v-divider>
        </div>
    </v-container>
</template>

<script>

    import {mapGetters} from 'vuex'

    export default {
        name: 'CancellationReportList',
        computed: {
            ...mapGetters(['sortedReports']),
        }
    }

</script>

<style scoped>

</style>