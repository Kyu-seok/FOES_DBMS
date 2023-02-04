import { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';

import Navbar from '../../../components/navbar/Navbar';
import Sidebar from '../../../components/sidebar/Sidebar';
import TableContainer from '../../../layout/table-container/TableContainer';
import URL from '../../../store/url';
import './researchAward.scss';

const ResearchAward = () => {

  const token = useSelector(state => state.auth.tokenId)
  const [columns, setColumns] = useState();
  const [rows, setRows] = useState();

  useEffect(() => {
    const fetchData = async () => {
      const response = await fetch(
        `${URL}/research-awards`, { 
          method: 'GET',
          headers: {
            Authorization : `Bearer ${token}`
          }
        }
      );

      if (!response.ok) {
        throw new Error('Could not fetch data!');
      }

      const data = await response.json();

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

      const filters = ["id"];

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
        {columns && <TableContainer title={'Research-Award'} viewCollection='Awards' columns={columns} rows={rows} setRows={setRows} api="research-awards"/>}
      </div>
    </div>
  );
};

export default ResearchAward;