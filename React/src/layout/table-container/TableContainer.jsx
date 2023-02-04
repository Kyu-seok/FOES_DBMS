import { Button } from '@mui/material';
import React from 'react';
import { Link } from 'react-router-dom';
import DataTable from '../../components/dataTable/DataTable';
import './tableContainer.scss';

const TableContainer = (props) => {

  const generateColumnArr = (arr) => {
    let columnArr = [];

    arr.forEach((colName) => {
      columnArr.push({ field: colName, headerName: colName, editable: true, width: '150' });
    });

    return columnArr;
  };

  const viewCollection = props.viewCollection;

  const columns = generateColumnArr(props.columns);
  const rows = props.rows;


  return (
    <div className="tableContainer">
      <div className="tableTitle">
        {props.title}
      </div>
      <div className="tableAction">
        <Button color="success" variant="contained">
          <Link to={`/${props.api}/new`} className="newButton">
            <p className="buttonText">Add New</p>
          </Link>
        </Button>
      </div>
      <DataTable rows={rows} columns={columns} setRows={props.setRows} viewCollection={viewCollection} api={props.api}/>
    </div>
  );
};

export default TableContainer;