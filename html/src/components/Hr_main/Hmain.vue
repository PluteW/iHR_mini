<template>
    <div>
        <div class="header_continer">
            <div class="header">
                <el-tabs v-model="activeName" type="card" id = "label_continer" stretch>
                    <el-tab-pane name="publish" label="发布" > </el-tab-pane>
                    <el-tab-pane name="polling" label="查询"> </el-tab-pane>
                    <el-tab-pane name="detail" label="详情"> </el-tab-pane>
                </el-tabs>
            </div>
        </div>
        <div v-if="activeName === 'publish'">
            <publish></publish>
        </div>
        <div v-if="activeName === 'polling'">
            <polling v-bind:cotent="activeName"></polling>
        </div>
        <div v-if="activeName === 'detail'">
            <detail></detail>
        </div>
    </div>
</template>

<script>
import Vue from 'vue'
import publish from './publish_new.vue'
import polling from './polling.vue'
import detail from './detail.vue'
export default Vue.extend({
  components: {
    publish,
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
