import React, { useEffect } from 'react';

const FieldSelector = ({ setSelectedFields, selectedFields }) => {
    const availableFields = ["dma_id", "dma_name", "week", "rank", "refresh_date"];
    const defaultFields = ["score", "term"]; // Default flieds

    useEffect(() => {
        setSelectedFields((prevSelectedFields) => {
            const fieldsToAdd = defaultFields.filter((field) => !prevSelectedFields.includes(field));
            return [...prevSelectedFields, ...fieldsToAdd];
        });
    }, [setSelectedFields]);

    const handleFieldSelect = (field) => {
        const isDefaultField = defaultFields.includes(field);

        if (isDefaultField) {
            if (!selectedFields.includes(field)) {
                setSelectedFields([...selectedFields, field]);
            }
        } else {
            const updatedFields = selectedFields.includes(field)
                ? selectedFields.filter((selected) => selected !== field)
                : [...selectedFields, field];
            setSelectedFields(updatedFields);
        }
    };

    return (
        <div>
            <h3>Select Fields</h3>
            {availableFields.map((field) => (
                <div key={field}>
                    <label>
                        <input
                            type="checkbox"
                            checked={selectedFields.includes(field)}
                            onChange={() => handleFieldSelect(field)}
                            disabled={defaultFields.includes(field)}
                        />
                        {field}
                    </label>
                </div>
            ))}
        </div>
    );
};

export default FieldSelector;
