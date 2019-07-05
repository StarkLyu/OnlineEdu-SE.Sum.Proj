import { expect } from 'chai'
import { mount } from '@vue/test-utils'
import Login from "../../src/modules/index/views/Login.vue"

describe('HelloWorld.vue', () => {
  it('renders props.msg when passed', () => {
    const msg = 'new message'
    const wrapper = mount(Login)
    expect(wrapper.text()).to.include(msg)
  })
})
