<<<<<<< HEAD
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
              <div class="informationCon">
                <div class="informationLeft">
                  <div style="font-size: 26px;margin-top:15px;margin-left: 0;width:75%; float:left">
                    岗位名：开发工程师
                  </div>
                  <div style="font-size: 22px;margin-top:15px;margin-left: 0;width:75%;float:left">
                    工作地点：广州佛山
                  </div>
                  <div style="font-size: 22px;margin-top:15px;margin-left: 0;width:75%;float:left">
                    薪资待遇：15k/月
                  </div>
                </div>
                <div class="informationRight">
                </div>
              </div>
              <div class="informationCon">
                <div class="informationLeft">
                </div>
                <div class="informationRight">
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
const eduBackGroundOptions = ['全选', '本科在读', '本科毕业', '研究生', '博士', '留学生']
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
      pageIndex: 0, // 初始化页数
      resultTotal: 0, // 数据总数
      region: ['山东', '北京', '上海', '广州'], // 应聘者来源
      time: ['2019-8', '2019-9', '2019-7'], // 可供选择简历投递时间
      jobRegin: ['开发岗', '管理岗', '技术岗', '人力岗', '财务岗'], // 可供选择岗位职类
      eduBackGround: ['专科及以上', '本科及以上', '研究生及以上', '博士', '留学生'], // 学历要求
      status: ['申请中', '简历筛选', '面试', '岗位确定', '过期'] // 可供选择岗位状态
    }
  },
  methods: {
    handleTableSelection (val) {
      this.multipleSelection = val
      console.log(this.multipleSelection)
    },
    handleCheck (val) {
      if (this.flagChecked) {
        this.form.eduBackGround = []
      } else {
        this.form.eduBackGround = eduBackGroundOptions
      }
      this.flagChecked = !this.flagChecked
    },
    handleView (row) {
      console.log(row.id)
      this.$bus.emit('employeeId', row.id)
      this.$emit('func', 'detail')
    },
    setPageIndex (val) {
      let _self = this
      _self.pageIndex = val
      _self.handleSubmit()
    },
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
            form: _self.form, // 保存了数据的对象
            pageIndex: _self.pageIndex
          },
          transformRequest: [function (data) {
            var params = Qs.stringify(data, { arrayFormat: 'brackets' })
            return params
          }],
          headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'},
          timeout: 15000
        }
      ).then(
        function (res) { // 如果返回数据，则放到表格中
          if (res.data) {
            _self.tableData = res.data.tableData
            _self.resultTotal = res.data.resultTotal
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
      _self.pageIndex = 1
      this.handleSubmit()
    },
    reset () {
      let loadingInstance = Loading.service({
        text: '请稍等...',
        target: document.querySelector('.loadingtext')
      })
      this.form.name = '' // 姓名
      this.form.region = [] // 应聘者来源
      this.form.sex = '' // 性别
      this.form.time = '' // 应聘时间
      this.form.jobRegin = '' // 应聘岗位
      this.form.status = '' // 简历状态
      this.form.scheduleTime = '' // 入职时间
      this.form.eduBackGround = [] // 学历选择项
      this.form.age = ''
      this.flagChecked = false
      this.chileDhecked = false
      loadingInstance.close()
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
  border: black solid 3px;
  height: 150px;
  width: 85%;
  margin: auto;
  margin-bottom: 2%
}
.informationLeft{
  border: blue solid 3px;
  height: 100%;
  width: 75%;
  float: left
}
.informationRight{
  border: red solid 3px;
  height: 100%;
  width: 20%;
  float: right
}
</style>
=======
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
              <div class="informationCon">
                <div class="informationLeft">
                  <div style="font-size: 26px;margin-top:15px;margin-left: 0;width:75%; float:left">
                    岗位名：开发工程师
                  </div>
                  <div style="font-size: 22px;margin-top:15px;margin-left: 0;width:75%;float:left">
                    工作地点：广州佛山
                  </div>
                  <div style="font-size: 22px;margin-top:15px;margin-left: 0;width:75%;float:left">
                    薪资待遇：15k/月
                  </div>
                </div>
                <div class="informationRight">
                </div>
              </div>
              <div class="informationCon">
                <div class="informationLeft">
                </div>
                <div class="informationRight">
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
const eduBackGroundOptions = ['全选', '本科在读', '本科毕业', '研究生', '博士', '留学生']
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
      pageIndex: 0, // 初始化页数
      resultTotal: 0, // 数据总数
      region: ['山东', '北京', '上海', '广州'], // 应聘者来源
      time: ['2019-8', '2019-9', '2019-7'], // 可供选择简历投递时间
      jobRegin: ['开发岗', '管理岗', '技术岗', '人力岗', '财务岗'], // 可供选择岗位职类
      eduBackGround: ['专科及以上', '本科及以上', '研究生及以上', '博士', '留学生'], // 学历要求
      status: ['申请中', '简历筛选', '面试', '岗位确定', '过期'] // 可供选择岗位状态
    }
  },
  methods: {
    handleTableSelection (val) {
      this.multipleSelection = val
      console.log(this.multipleSelection)
    },
    handleCheck (val) {
      if (this.flagChecked) {
        this.form.eduBackGround = []
      } else {
        this.form.eduBackGround = eduBackGroundOptions
      }
      this.flagChecked = !this.flagChecked
    },
    handleView (row) {
      console.log(row.id)
      this.$bus.emit('employeeId', row.id)
      this.$emit('func', 'detail')
    },
    setPageIndex (val) {
      let _self = this
      _self.pageIndex = val
      _self.handleSubmit()
    },
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
            form: _self.form, // 保存了数据的对象
            pageIndex: _self.pageIndex
          },
          transformRequest: [function (data) {
            var params = Qs.stringify(data, { arrayFormat: 'brackets' })
            return params
          }],
          headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'},
          timeout: 15000
        }
      ).then(
        function (res) { // 如果返回数据，则放到表格中
          if (res.data) {
            _self.tableData = res.data.tableData
            _self.resultTotal = res.data.resultTotal
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
      _self.pageIndex = 1
      this.handleSubmit()
    },
    reset () {
      let loadingInstance = Loading.service({
        text: '请稍等...',
        target: document.querySelector('.loadingtext')
      })
      this.form.name = '' // 姓名
      this.form.region = [] // 应聘者来源
      this.form.sex = '' // 性别
      this.form.time = '' // 应聘时间
      this.form.jobRegin = '' // 应聘岗位
      this.form.status = '' // 简历状态
      this.form.scheduleTime = '' // 入职时间
      this.form.eduBackGround = [] // 学历选择项
      this.form.age = ''
      this.flagChecked = false
      this.chileDhecked = false
      loadingInstance.close()
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
  border: black solid 3px;
  height: 150px;
  width: 85%;
  margin: auto;
  margin-bottom: 2%
}
.informationLeft{
  border: blue solid 3px;
  height: 100%;
  width: 75%;
  float: left
}
.informationRight{
  border: red solid 3px;
  height: 100%;
  width: 20%;
  float: right
}
</style>
>>>>>>> 4e7324683b09f6eac8f7f1fe025057a545537c27
