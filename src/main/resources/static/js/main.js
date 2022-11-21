Vue.component('commodity-row', {
    props: ['commodity'],
    template: '<div> </div>',
    methods: {},


});
//
// Vue.component('price-list', {
//    props: ['price', 'commodities],
//    data: function () {
//        return {
//            product: null
//        }
//    },
//     template:
// });

Vue.component('main-page', {
    template:
        '<div>' +
        '<input type="button" value="Begin purchase" @click="newPurchase">' +
        '</div>'


});

var app = new Vue({
    el: '#app',
    template: '<main-page />',
    data: {}
});