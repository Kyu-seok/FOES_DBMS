import * as React from 'react';
import { DataGrid, GridToolbar } from '@mui/x-data-grid';

import Snackbar from '@mui/material/Snackbar';
import Alert from '@mui/material/Alert';
import { useSelector } from 'react-redux';
import { Link } from 'react-router-dom';
import './dataTable.scss';
import { Button } from '@mui/material';
import URL from '../../store/url';

const useFakeMutation = () => {
  return React.useCallback(
    (user) =>
      new Promise((resolve, reject) =>
        setTimeout(() => {
          if (user.name?.trim() === '') {
            reject(new Error("Error while saving user: name can't be empty."));
          } else {
            resolve({ ...user, name: user.name });
          }
        }, 200),
      ),
    [],
  );
};

const DataTable = (props) => {

  const token = useSelector(state => state.auth.tokenId)

  const mutateRow = useFakeMutation();
  const [snackbar, setSnackbar] = React.useState(null);
  const handleCloseSnackbar = () => setSnackbar(null);

  const processRowUpdate = React.useCallback(
    async (newRow) => {
      const { id: elementID, ...newObj } = newRow;
      await fetch(
        `${URL}/${props.api}/${elementID}`,
        {
          method: 'put',
          body: JSON.stringify(newObj),
          headers: {
            Authorization : `Bearer ${token}`
          }
        }
        );

      const response = await mutateRow(newRow);
      setSnackbar({ children: 'Successfully saved', severity: 'success' });
      return response;
    },
    [mutateRow, token, props.api],
  );

  const handleProcessRowUpdateError = React.useCallback((error) => {
    setSnackbar({ children: error.message, severity: 'error' });
  }, []);

  const onDeleteEntryHandler = (api, id) => {
    if (window.confirm("Are you sure you want to delete this element?") == true) {
      fetch(`${URL}/${api}/${id}`, {
        method: 'DELETE',
        headers: {
          Authorization : `Bearer ${token}`
        }
      })
      .then(props.setRows(props.rows.filter(item => item.id !== id)))
    }
 
  };

  const actionColumn = [
    {
      field: "action",
      headerName: "Action",
      width: 200,
      renderCell: (params) => {
        return (
          <div className="cellAction">
            <Link to={`/${props.api}/${params.row.id}`} style={{ textDecoration: "none" }} >
              <Button className="viewButton">View</Button>
            </Link>
            <Button
              className="deleteButton"
              color='error'
              onClick={() => onDeleteEntryHandler(props.api, params.row.id)}
            >
              Delete
            </Button>
          </div>
        );
      },
    },
  ];

  return (
    <div style={{ height: 600, width: '100%' }} className='dataTable'>
      <DataGrid
        rows={props.rows}
        columns={props.columns.concat(actionColumn)}
        rowsPerPageOptions={[10, 25, 50, 100]}
        processRowUpdate={processRowUpdate}
        onProcessRowUpdateError={handleProcessRowUpdateError}
        experimentalFeatures={{ newEditingApi: true }}
        components={{Toolbar: GridToolbar}}
      />
      {!!snackbar && (
        <Snackbar
          open
          anchorOrigin={{ vertical: 'bottom', horizontal: 'center' }}
          onClose={handleCloseSnackbar}
          autoHideDuration={6000}
        >
          <Alert {...snackbar} onClose={handleCloseSnackbar} />
        </Snackbar>
      )}
    </div>
  );
};

export default DataTable;