<<<<<<< HEAD
<template>
    <div>
      <div class="header_continer">
            <div class="header">
                <el-tabs v-model="activeName"  type="card" id = "label_continer" stretch>
                    <el-tab-pane name="polling"  id="polling" label="查询"> </el-tab-pane>
                    <el-tab-pane name="detail" id="detail" label="详情"> </el-tab-pane>
                    <el-tab-pane name="mySign" id="mySign" label="我的" > </el-tab-pane>
                </el-tabs>
            </div>
        </div>
        <div v-if="activeName === 'mySign'">
            <mySign></mySign>
        </div>
        <div v-if="activeName === 'polling'">
            <polling v-bind:cotent="activeName" v-on:func = 'changeView'></polling>
        </div>
        <div v-if="activeName === 'detail'">
            <detail></detail>
        </div>
    </div>
</template>

<script>
import Vue from 'vue'
import polling from './polling.vue'
import mySign from './mySign.vue'
import detail from './detail'
export default Vue.extend({
  components: {
    mySign,
    polling,
    detail
  },
  data () {
    return {
      activeName: 'polling',
      blocks: [
        { name: 'publish', label: '发布' },
        { name: 'polling', label: '查询' },
        { name: 'detail', label: '详情' }
      ]
    }
  },
  methods: {
    changeView (choose) {
      this.activeName = 'detail'
    }
  }
})
</script>
<style>
.header_continer{
    border-bottom: rgb(39, 146, 196) 2px double;
    width: 90%;
    padding-top: 10px;
    margin: auto;
}
.header{
    width: 50%;
    margin-left: 8%;
    height: 50px;
}
</style>
=======
<template>
    <div>
      <div class="header_continer">
            <div class="header">
                <el-tabs v-model="activeName"  type="card" id = "label_continer" stretch>
                    <el-tab-pane name="polling"  id="polling" label="查询"> </el-tab-pane>
                    <el-tab-pane name="detail" id="detail" label="详情"> </el-tab-pane>
                    <el-tab-pane name="mySign" id="mySign" label="我的" > </el-tab-pane>
                </el-tabs>
            </div>
        </div>
        <div v-if="activeName === 'mySign'">
            <mySign></mySign>
        </div>
        <div v-if="activeName === 'polling'">
            <polling v-bind:cotent="activeName" v-on:func = 'changeView'></polling>
        </div>
        <div v-if="activeName === 'detail'">
            <detail></detail>
        </div>
    </div>
</template>

<script>
import Vue from 'vue'
import polling from './polling.vue'
import mySign from './mySign.vue'
import detail from './detail'
export default Vue.extend({
  components: {
    mySign,
    polling,
    detail
  },
  data () {
    return {
      activeName: 'polling',
      blocks: [
        { name: 'publish', label: '发布' },
        { name: 'polling', label: '查询' },
        { name: 'detail', label: '详情' }
      ]
    }
  },
  methods: {
    changeView (choose) {
      this.activeName = 'detail'
    }
  }
})
</script>
<style>
.header_continer{
    border-bottom: rgb(39, 146, 196) 2px double;
    width: 90%;
    padding-top: 10px;
    margin: auto;
}
.header{
    width: 50%;
    margin-left: 8%;
    height: 50px;
}
</style>
>>>>>>> 4e7324683b09f6eac8f7f1fe025057a545537c27
