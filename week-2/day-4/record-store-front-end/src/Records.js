import { useState, useEffect } from 'react';
import './Records.css';
import RecordCard from './RecordCard.js';
import RecordForm from './RecordForm.js';

function Records() {

    const [records, setRecords] = useState([]);
    const [showForm, setShowForm] = useState(false);
    const [scopedRecord, setScopedRecord] = useState({});
    const [error, setError] = useState();

    useEffect(() => {
        fetch("http://localhost:8080/records")
        .then(response => response.json())
        .then(result => setRecords(result))
        .catch(console.log);
    }, []);


    function addClick() {
        const now = new Date();
        setScopedRecord({ id: 0, artist: "", album: "", year: now.getFullYear() });
        setShowForm(true);
    }

    function notify({ action, record, error }) {
        if (error) {
            setError(error);
            setShowForm(false);
            return;
        }
        switch (action) {
            case "delete":
                setRecords(records.filter(r => r.id !== record.id))
                break;
            case "edit":
                setRecords(records.map(r => {
                    if (r.id === record.id) {
                        return record;
                    }
                    return r;
                }));
                break;
            case "edit-form":
                setShowForm(true);
                setScopedRecord(record);
                return;
            case "add":
                setRecords([...records, record]);
                break;
            default:
                console.log("INVALID ACTION!", action);
                console.log("also this... INVALID ACTION!" + action);
                alert("INVALID ACTION! " + action);
        }

        setError("");
        setShowForm(false);
    }

    if (showForm) {
        return <RecordForm record={scopedRecord} notify={notify} />
    }

    return (
        <>
            {error && <div className="alert alert-danger">{error}</div>}
            <div>
                <h1 id='recordTitle'>Records</h1>
                <button className="btn btn-primary" type="button" onClick={addClick}>Add a Record</button>
                <table id='records'>
                    <tr>
                        <th>Artist</th>
                        <th>Album</th>
                        <th>Year</th>
                        <th>Actions</th>
                    </tr>
                    <tbody>
                        {records.map(r => <RecordCard key={r.recordId} record={r} notify={notify} />)}
                    </tbody>
                </table>
            </div>
        </>
    )
}

export default Records;