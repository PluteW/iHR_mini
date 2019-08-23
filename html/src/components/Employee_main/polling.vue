<template>
    <div>
        <div style="margin: 40px 40px auto 40px">
          <el-form ref="form" :model="form" label-width="120px">
              <el-row>
                  <el-col :span="6">
                      <el-form-item label="岗位：" label-width="100px" label-position = 'right'>
                          <el-input v-model="form.name" placeholder="请输入岗位"></el-input>
                      </el-form-item>
                  </el-col>
                  <el-col :span="6">
                      <el-form-item label="工作地点：" label-width="100px" label-position = 'right'>
                          <el-select v-model="form.region" placeholder="请选择工作地点" clearable>
                            <el-option v-for="(item,index) in region" :key="index"  :value="index" :label="item" ></el-option>
                        </el-select>
                      </el-form-item>
                  </el-col>
                  <el-col :span="6">
                      <el-form-item label="入职时间：" label-width="100px" label-position = 'left'>
                          <el-date-picker v-model="form.scheduleTime"  type="month"  placeholder="请选择入职时间" clearable> </el-date-picker>
                      </el-form-item>
                  </el-col>
                  <el-col :span="5">
                      <el-form-item label="邮箱提醒" label-width="150px" label-position = 'right'>
                          <el-switch v-model="form.emailRemind"></el-switch>
                      </el-form-item>
                  </el-col>
              </el-row>
              <el-row style="margin-top: 15px">
                  <el-col :span="6" label-width="100px">
                      <el-form-item label="职位分类：" label-position = 'right'>
                            <el-select v-model="form.age" placeholder="请选择职类" clearable>
                              <el-option v-for="(item,index) in jobRegin" :key="index" :value="index" :label="item" ></el-option>
                          </el-select>
                      </el-form-item>
                  </el-col>
                  <el-col :span="6">
                      <el-form-item label="学历要求：" label-width="100px" label-position = 'right'>
                          <el-select v-model="form.eduBackGround" placeholder="学历要求" clearable>
                              <el-option v-for="(item,index) in eduBackGround" :key="index" :value="index" :label="item" ></el-option>
                          </el-select>
                      </el-form-item>
                  </el-col>
                  <el-col :span="6">
                      <el-form-item label="岗位状态：" label-width="100px" label-position = 'right'>
                          <el-select v-model="form.status" placeholder="请选择岗位状态" clearable>
                              <el-option v-for="(item,index) in status" :key="index" :value="index" :label="item" ></el-option>
                          </el-select>
                      </el-form-item>
                  </el-col>
              </el-row>
              <el-form-item style="margin-left:60% ;margin-top: 25px">
                  <el-button type="primary" @click="onSubmit" style="margin-right: 7%">查询</el-button>
                  <el-button @click="reset">重置</el-button>
              </el-form-item>
            </el-form>
            <div style="margin-top: 30px">
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
                  <el-button @click="hold" v-bind="item" style="margin-left: 15%;margin-top: 15%">收藏</el-button>
                  <el-button @click="delet"  v-bind="item" style="margin-right:3%">删除</el-button>
                  <el-button type="primary" v-bind="item" @click="detail(item)">详情</el-button>
                </div>
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
// let loadingInstance = Loading.service({
//   text: '请稍等...',
//   target: document.querySelector('.loadingtext')
// })
export default {
  name: 'polling',
  props: ['cotent'],
  data () {
    return {
      message: this.cotent,
      form: {
        name: '', // 岗位名称
        region: '', // 工作地点
        scheduleTime: '', // 入职时间
        emailRemind: false, // 邮箱提醒，默认不提醒
        jobRegin: '', // 应聘岗位
        status: '', // 简历状态
        eduBackGround: '' // 学历选择项
      },
      chooseType: 0, // 选项代号 0初始化 1收藏 2删除 3详情
      region: ['山东', '北京', '上海', '广州'], // 应聘者来源
      time: ['2019-8', '2019-9', '2019-7'], // 可供选择简历投递时间
      jobRegin: ['开发岗', '管理岗', '技术岗', '人力岗', '财务岗'], // 可供选择岗位职类
      eduBackGround: ['专科及以上', '本科及以上', '研究生及以上', '博士', '留学生'], // 学历要求
      status: ['申请中', '简历筛选', '面试', '岗位确定', '过期'], // 可供选择岗位状态
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
          form: '' // 空白表单用于全部查询
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
          _self.informationData = res.data.informationData // 全部工作列表
          _self.region = res.data.region // 工作地区列表
          _self.time = res.data.time // 到岗时间列表
          _self.eduBackGround = res.data.eduBackGround // 教育背景列表
          _self.status = res.data.status // 岗位状态列表
          _self.jobRegin = res.data.jobRegin // 岗位类别列表
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
            message: '初始化失败'
          })
        }
      }
    )
    loadingInstance.close()
  },
  methods: {
    handleSubmit () {
      let _self = this
      let loadingInstance = Loading.service({
        text: '请稍等...',
        target: document.querySelector('.loadingtext')
      })
      axios(
        {
          method: 'post',
          url: 'ccs/', // 请求数据地址
          data: {
            form: _self.form, // 保存了数据的表单对象
            chooseType: _self.chooseType, // 状态码
            region: _self.region, // 工作地区列表
            time: _self.time, // 到岗时间列表
            eduBackGround: _self.eduBackGround, // 教育背景列表
            status: _self.status, // 岗位状态列表
            jobRegin: _self.jobRegin // 岗位类别列表
          },
          transformRequest: [function (data) {
            var params = Qs.stringify(data, { arrayFormat: 'brackets' })
            return params
          }],
          headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'},
          timeout: 15000
        }
      ).then(
        function (res) { // 如果返回数据，则放到列表中
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
              message: response
            })
          }
        }
      )
      loadingInstance.close()
    },
    onSubmit () {
      console.log(this.form)
      let _self = this
      _self.chooseType = 0
      _self.handleSubmit()
    },
    reset () {
      let _self = this
      let loadingInstance = Loading.service({
        text: '请稍等...',
        target: document.querySelector('.loadingtext')
      })
      _self.chooseType = 0 // 选项代号初始化
      _self.form.name = '' // 岗位名称
      _self.form.region = '' // 工作地点
      _self.form.time = '' // 应聘时间
      _self.form.jobRegin = '' // 应聘岗位
      _self.form.status = '' // 岗位状态
      _self.form.scheduleTime = '' // 入职时间
      _self.form.eduBackGround = '' // 学历选择项
      _self.form.emailRemind = false // 邮箱提醒，默认不提醒
      loadingInstance.close()
    },
    hold () {
      let _self = this
      _self.chooseType = 1 // 收藏
      _self.handleSubmit()
    },
    delet () {
      let _self = this
      _self.chooseType = 2 // 取消显示
      _self.handleSubmit()
    },
    detail (item) { // 转到详情页
      console.log(item)
      let _self = this
      _self.chooseType = 3 // 转到详情页
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
