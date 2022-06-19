import React from 'react';
import { Button, Form, FormGroup, Label, Input } from 'reactstrap';

class AddEditForm extends React.Component {
  state = {
    id: 0,
    description: '',
    progress: '',
    title: '',
    todo_date: ''
  }

  onChange = e => {
    this.setState({[e.target.name]: e.target.value})
  }

  submitFormAdd = e => {
    e.preventDefault()
    fetch('http://localhost:8080/todo/create', {
      method: 'post',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        description: this.state.description,
        progress: this.state.progress,
        title: this.state.title,     
        //todo_date: this.state.todo_date
      })
    })
      .then(response => response.json())
      .then(item => {
        if(Array.isArray(item)) {
          this.props.addItemToState(item[0])
          this.props.toggle()
        } else {
          console.log('failure')
        }
      })
      .catch(err => console.log(err))
  }



  submitFormEdit = e => {
    e.preventDefault()

    
      //need ID 
    fetch('http://localhost:8080/todo/update/', {
      method: 'put',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        description: this.state.description,
        progress: this.state.progress,
        title: this.state.title,       
        //todo_date: this.state.todo_date
      })
    })
      .then(response => response.json())
      .then(item => {
        if(Array.isArray(item)) {
          // console.log(item[0])
          this.props.updateState(item[0])
          this.props.toggle()
        } else {
          console.log('failure')
        }
      })
      .catch(err => console.log(err))
  }

  componentDidMount(){
    // if item exists, populate the state with proper data
    if(this.props.item){
      const { id, description, progress, title } = this.props.item
      this.setState({ id, description, progress, title })
    }
  }

  render() {
    return (
      <Form onSubmit={this.props.item ? this.submitFormEdit : this.submitFormAdd}>
        <FormGroup>
          <Label for="description">Description </Label>
          <Input type="text" name="description" id="description" onChange={this.onChange} value={this.state.description === null ? '' : this.state.description} />
        </FormGroup>
        <FormGroup>
          <Label for="progress">Progress </Label>
          <Input type="text" name="progress" id="progress" onChange={this.onChange} value={this.state.progress === null ? '' : this.state.progress}  />
        </FormGroup>
        <FormGroup>
          <Label for="title">Title</Label>
          <Input type="text" name="title" id="title" onChange={this.onChange} value={this.state.title === null ? '' : this.state.title}  />
        </FormGroup>       
        {/* <FormGroup>
          <Label for="todo_date">Todo Date</Label>
          <Input type="date" name="todo_date" id="todo_date" onChange={this.onChange} value={this.state.todo_date}  />
        </FormGroup> */}
        <Button
         color="success"
         >Submit</Button>
      </Form>
    );
  }
}

export default AddEditForm