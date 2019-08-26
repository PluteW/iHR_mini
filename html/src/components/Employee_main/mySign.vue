<template>
    <div>
      <div style="margin-top: 50px">
        <div class="informationCon" :v-bind="informationData" v-for="(item,i) in informationData" :key="i">
          <div class="informationLeft">
            <div class="title">
              岗  位：{{item.name}}
            </div>
            <div class="location">
              工作地点：{{item.location}}
            </div>
            <div class="status">
              岗位状态：{{item.status}}
            </div>
            <div class="salary">
              薪资待遇：{{item.salary}}/月
            </div>
          </div>
          <div class="informationRight">
            <el-button type="primary" v-bind="item" style="margin-left: 17%;margin-top: 17%"
            @click="detail(item)">详情</el-button>
          </div>
        </div>
      </div>
    </div>
</template>

<script>
import { Loading, Message } from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import axios from 'axios'
import Qs from 'qs'
export default {
  name: 'polling',
  props: ['cotent'],
  data () {
    return {
      informationData: [{
        id: '1234234',
        name: 'IT开发工程师',
        location: '广州',
        salary: '3000',
        status: '申请中'
      }, {
        id: '1234234',
        name: 'IT开发工程师',
        location: '广州',
        salary: '3000',
        status: '申请中'
      }, {
        id: '1234234',
        name: 'IT开发工程师',
        location: '广州',
        salary: '3000',
        status: '申请中'
      }, {
        id: '1234234',
        name: 'IT开发工程师',
        location: '广州',
        salary: '3000',
        status: '申请中'
      }]
    }
  },
  mounted: function () {
    let _self = this
    let loadingInstance = Loading.service({
      text: '请稍等...',
      target: document.querySelector('.loadingtext')
    })
    _self.chooseType = 0
    axios(
      {
        method: 'post',
        url: 'ccs/', // 页面初始化，请求数据地址
        data: {
          // employeeId: localStorage.getItem('userdata').id // 用户的id
        },
        transformRequest: [function (data) {
          var params = Qs.stringify(data, { arrayFormat: 'brackets' })
          return params
        }],
        headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'},
        timeout: 15000
      }
    ).then(
      function (res) { // 如果返回数据，则放到展示中
        if (res.data) {
          _self.informationData = res.data.informationData
          Message({
            type: 'success',
            message: '查询成功'
          })
          loadingInstance.close()
        }
      }, function (response) { // 如果返回错误，则提错
        if (response) {
          loadingInstance.close()
          Message({
            type: 'error',
            message: '查询失败'
          })
        }
      }
    )
    loadingInstance.close()
  },
  methods: {
    detail (item) { // 转到详情页
      console.log(item)
      let _self = this
      _self.chooseType = 3
      this.$bus.emit('jobId', item.id)
      this.$emit('func', 'detail')
    }
  }
}
</script>
<style scoped>
.float-right {
    float: right;
    margin-left: 70%;
    margin-right: 8%;
    margin-top: 2%
}
.informationCon{
  border: rgb(39, 146, 196) solid 3px;
  height: 150px;
  width: 85%;
  margin: auto;
  margin-bottom: 2%
}
.informationLeft{
  height: 100%;
  width: 65%;
  float: left
}
.informationRight{
  height: 100%;
  width: 30%;
  float: right
}
.title{
  font-size:26px;
  margin-top:15px;
  margin-left: 10%;
  width:65%;
  float:left
}
.location{
  font-size: 22px;
  margin-top:15px;
  margin-left: 10%;
  width:40%;
  float:left
}
.status{
  color: cornflowerblue;
  font-size: 18px;
  margin-top:20px;
  margin-left: 10%;
  width:25%;
  float:left
}
.salary{
  font-size: 22px;
  margin-top:15px;
  margin-left: 10%;
  width:65%;
  float:left
}
</style>
