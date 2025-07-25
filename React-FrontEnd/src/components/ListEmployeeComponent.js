import React, {useState, useEffect} from 'react'
import { Link } from 'react-router-dom'
import EmployeeService from '../services/EmployeeService'

const ListEmployeeComponent = () => {

    const [employees, setEmployees] = useState([])

    useEffect(() => {

        getAllEmployees();
    }, [])

    const getAllEmployees = () => {
        EmployeeService.getAllEmployees().then((response) => {
            setEmployees(response.data)
            console.log(response.data);
        }).catch(error =>{
            console.log(error);
        })
    }

    const deleteEmployee = (employeeId) => {

      const result = window.confirm("정말 삭제하시겠습니까?");
      if (result) {

        // 여기에 실제 삭제 로직을 넣을 수 있습니다.
        EmployeeService.deleteEmployee(employeeId).then((response) =>{
        getAllEmployees();
        alert("삭제되었습니다.");
       }).catch(error =>{
           console.log(error);
       })
      } else {
        alert("삭제가 취소되었습니다.");
      }
    }

    return (
        <div className = "container">
            <h2 className = "text-center"> 사원 리스트 </h2>
            <Link to = "/add-employee" className = "btn btn-primary mb-2" > 사원 추가 </Link>
            <table className="table table-bordered table-striped">
                <thead>
                    <tr>
                        <th> 번호 </th>
                        <th> 성 </th>
                        <th> 이름 </th>
                        <th> 메일 </th>
                        <th> Actions </th>
                    </tr>
                </thead>
                <tbody>
                    {
                        employees.map(
                            employee =>
                            <tr key = {employee.id}> 
                                <td> {employee.id} </td>
                                <td>{employee.lastName}</td>
                                <td> {employee.firstName} </td>
                                <td>{employee.emailId}</td>
                                <td>
                                    <Link className="btn btn-info" to={`/edit-employee/${employee.id}`} > 수정 </Link>
                                    <button className = "btn btn-danger" onClick = {() => deleteEmployee(employee.id)}
                                    style = {{marginLeft:"10px"}}> 삭제 </button>
                                </td>
                            </tr>
                        )
                    }
                </tbody>
            </table>
        </div>
    )
}

export default ListEmployeeComponent
