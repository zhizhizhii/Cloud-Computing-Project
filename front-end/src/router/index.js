import Vue from 'vue';
import VueRouter from "vue-router";
import Index from "../components/Index"
import Gender from "../components/Gender";
import CategoryTop from "../components/CategoryTop";
import MonthTotal from "../components/MonthTotal";
import MonthProfit from "../components/MonthProfit";
import ProductTop from "../components/ProductTop";
import ThreeMonth from "../components/ThreeMonth";
import AllProduct from "../components/AllProduct";
import ProductByCategory from "../components/ProductByCategory";
import SearchPurchase from "../components/SearchPurchase";
import SearchProduct from "../components/SearchProduct";

Vue.use(VueRouter);

export default new VueRouter({

  routes:[
    {
      path:'/gender',
      component:Gender
    },
    {
      path:'/category',
      component:CategoryTop
    },
    {
      path:'/total',
      component:MonthTotal
    },
    {
      path:'/profit',
      component:MonthProfit
    },
    {
      path:'/product',
      component:ProductTop
    },
    {
      path:'/three',
      component:ThreeMonth
    },
    {
      path:'/',
      redirect:'/allProduct',
      component:Index
    },
    {
      path:'/allProduct',
      component:AllProduct
    },
    {
      path:'/productByCategory',
      component:ProductByCategory
    },
    {
      path:'/purchase',
      component:SearchPurchase,
    },
    {
      path:'/searchProduct',
      component:SearchProduct,
    }

  ]

});
