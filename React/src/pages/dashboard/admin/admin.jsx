import { useState } from 'react';
import { useEffect } from 'react';
import { useSelector } from 'react-redux';

import Navbar from '../../../components/navbar/Navbar';
import Sidebar from '../../../components/sidebar/Sidebar';
import TableContainer from '../../../layout/table-container/TableContainer';
import URL from '../../../store/url';
import './admin.scss';

const Admin = () => {

  const token = useSelector(state => state.auth.tokenId)

  const [columns, setColumns] = useState();
  const [rows, setRows] = useState();

  useEffect(() => {
    const fetchData = async () => {
      const readAllResponse = await fetch(
        `${URL}/users`, {
          headers: {
            Authorization : `Bearer ${token}`
          }
        }
      );

      if (!readAllResponse.ok) {
        throw new Error('Could not fetch data!');
      }

      const data = await readAllResponse.json();

      let columnArr = [];
      let entries = [];
      for (let entry in data) {
        var { [entry]: entryObj } = data;
        entries.push({ ...entryObj, id: entry });
        for (let key in entryObj) {
          if (!columnArr.includes(key)) {
            columnArr.push(key);
          }
        }
      }

      const filters = ["id", "createdAt", "updateAt", "password", "authorities", "accountNonExpired", "accountNonLocked", "credentialsNonExpired"];

      setColumns(columnArr.filter((column) => !filters.includes(column)));
      setRows(data);
    };

    fetchData();
  }, [token]);

  return (
    <div className="home">
      <Sidebar />
      <div className="homeContainer">
        <Navbar />
        {columns && <TableContainer title={'Admin'} viewCollection='Admin' columns={columns} rows={rows} setRows={setRows} api="users"/>}
      </div>
    </div>
  );
};

export default Admin;
